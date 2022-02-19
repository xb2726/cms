package com.serendipity.extra.query;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther bin
 */
public class QueryWrapperUtils {
    public static void initCondition(Object entity, QueryWrapper<?> wrapper) {
        if (entity == null) {
            return;
        }

        Class clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtil.isEmpty(fields)) {
            return;
        }

        for (Field field : fields) {
            field.setAccessible(true);
            Condition condition = field.getAnnotation(Condition.class);
            // 无condition的跳过
            if (condition == null) {
                continue;
            }
            // 静态常量跳过
            if (ReflectionUtils.isPublicStaticFinal(field)) {
                continue;
            }

            // 没有值的跳过
            Object value = ReflectUtil.getFieldValue(entity, field);
            if (value == null || "".equals(value.toString().trim())) {
                continue;
            }

            String target = condition.target();
            if (StringUtils.isEmpty(target)) {
                target =  field.getName();
            }
            MatchMode matchMode = condition.machMode();

            // 封装条件
            if (MatchMode.EQ.equals(matchMode)) {
                wrapper.eq(target, value);
                continue;
            }

            if (MatchMode.GE.equals(matchMode)) {
                wrapper.ge(target, value);
                continue;
            }

            if (MatchMode.GT.equals(matchMode)) {
                wrapper.gt(target, value);
                continue;
            }

            if (MatchMode.LE.equals(matchMode)) {
                wrapper.le(target, value);
                continue;
            }

            if (MatchMode.LT.equals(matchMode)) {
                wrapper.lt(target, value);
                continue;
            }

            if (MatchMode.LIKE.equals(matchMode)) {
                LikeMode likeMode = condition.likeMode();

                if (LikeMode.ANYWHERE.equals(likeMode)) {
                    wrapper.like(target, value);
                    continue;
                }

                if (LikeMode.START.equals(likeMode)) {
                    wrapper.likeRight(target, value);
                    continue;
                }

                if (LikeMode.END.equals(likeMode)) {
                    wrapper.likeLeft(target, value);
                    continue;
                }
            }

            if (MatchMode.NE.equals(matchMode)) {
                wrapper.ne(target, value);
                continue;
            }

            if (MatchMode.NULL.equals(matchMode)) {
                Assert.isTrue(Boolean.class.isAssignableFrom(value.getClass()), "NULL条件必须为BOOLEAN类型");
                wrapper.isNull((boolean) value, target);
                continue;
            }

            if (MatchMode.NOT_NULL.equals(matchMode)) {
                Assert.isTrue(Boolean.class.isAssignableFrom(value.getClass()), "NOT_NULL条件必须为BOOLEAN类型");
                wrapper.isNotNull((boolean) value, target);
                continue;
            }

            if (MatchMode.IN.equals(matchMode)) {
                Assert.isTrue(List.class.isAssignableFrom(value.getClass()), "IN条件必须为LIST类型");
                wrapper.in(target, (List) value);
                continue;
            }

            if (MatchMode.NOT_IN.equals(matchMode)) {
                Assert.isTrue(List.class.isAssignableFrom(value.getClass()), "NOT_IN条件必须为LIST类型");
                wrapper.notIn(target, (List) value);
                continue;
            }

            List<MatchMode> dayList = new ArrayList<>();
            dayList.add(MatchMode.DAY_GE);
            dayList.add(MatchMode.DAY_EQ);
            dayList.add(MatchMode.DAY_LT);
            dayList.add(MatchMode.MONTH_EQ);
            if (dayList.contains(matchMode)) {
                Assert.isTrue(Date.class.isAssignableFrom(value.getClass()),
                        "DAY_GE、DAY_EQ、DAY_LT、MONTH_EQ匹配类型的必须是date.util类型的");
                Date dateValue = (Date) value;
                Date startDate = DateUtil.beginOfDay(dateValue);
                Date endDate = DateUtil.endOfDay(dateValue);
                if (MatchMode.DAY_EQ.equals(matchMode)) {
                    wrapper.between(target, startDate, endDate);
                    continue;
                }
                if (MatchMode.DAY_GE.equals(matchMode)) {
                    wrapper.ge(target, startDate);
                    continue;
                }
                if (MatchMode.DAY_LT.equals(matchMode)) {
                    wrapper.le(target, endDate);
                    continue;
                }
                if (MatchMode.MONTH_EQ.equals(matchMode)) {
                    Date monthStart = DateUtil.beginOfMonth(dateValue);
                    Date monthEnd = DateUtil.endOfMonth(dateValue);
                    wrapper.between(target, monthStart, monthEnd);
                    continue;
                }
            }
            if (MatchMode.OR.equals(matchMode)) {
                String[] targets = condition.targets();
                Assert.isTrue(ArrayUtil.isNotEmpty(targets), "OR类型targets不能为空");
                wrapper.and(wrapper1 -> {
                    for (String t : targets) {
                        // %表示使用like
                        if (t.startsWith("%")) {
                            wrapper1.or().like(t.substring(1), value.toString());
                        } else if (t.startsWith("=")) {
                            wrapper1.or().eq(t.substring(1), value);
                        } else if (t.startsWith(">=")) {
                            wrapper1.or().ge(t.substring(2), value);
                        } else if (t.startsWith(">")) {
                            wrapper1.or().gt(t.substring(1), value);
                        } else if (t.startsWith("<=")) {
                            wrapper1.or().le(t.substring(2), value);
                        } else if (t.startsWith("<")) {
                            wrapper1.or().lt(t.substring(1), value);
                        } else if (t.startsWith("@@")) {
                            wrapper1.or().isNull(t.substring(2));
                        } else if (t.startsWith("@")) {
                            wrapper1.or().isNotNull(t.substring(1));
                        } else {
                            wrapper1.or().eq(t, value);
                        }
                    }
                });
            }
        }
    }

}
