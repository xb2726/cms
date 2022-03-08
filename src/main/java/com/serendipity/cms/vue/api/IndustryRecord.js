import request from '@/utils/request'



export function add(data) {
    return request({
        url: '/com.serendipity.cms/industryRecord'+'/add',
        method: 'post',
        params: data
    })
}

export function deleteBatch(data) {
    return request({
        url: '/com.serendipity.cms/industryRecord'+'/deleteBatch',
        method: 'delete',
        params: data
    })
}

export function update(data) {
    return request({
       url: '/com.serendipity.cms/industryRecord'+'/update',
        method: 'post',
        params: data
    })
}

export function queryById(id) {
    return request({
        url: '/com.serendipity.cms/industryRecord'+'/queryById',
        method: 'get',
        params: { id }
    })
}

export function list(query) {
    return request({
        url: '/com.serendipity.cms/industryRecord'+'/list',
        method: 'get',
        params: query
    })
}

export function pageList(query) {
    return request({
        url: '/com.serendipity.cms/industryRecord'+'/pageList',
        method: 'get',
        params:  query
    })
}

export function exportXls(query) {
    return request({
        url: '/com.serendipity.cms/industryRecord'+'/exportXls',
        method: 'get',
        params: query
    })
}

export function importExcel(data) {
    return request({
        url: '/com.serendipity.cms/industryRecord'+'/importExcel',
        method: 'post',
        params: data
    })
}




