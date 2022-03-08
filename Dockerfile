FROM java:8

EXPOSE 9090

ENV MODE "pro"
ENV JAVA_OPTS ""
ENV JAVA_PARAMS ""
ENV DEF_JAVA_OPTS "-Djava.security.egd=file:/dev/./urandom -Duser.timezone=Asia/Shanghai -Dfile.encoding=utf-8"


ADD cms-1.0.jar app.jar
RUN bash -c 'touch /app.jar'


ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} ${DEF_JAVA_OPTS} -jar /app.jar ${JAVA_PARAMS} --spring.profiles.active=${MODE}" ]
