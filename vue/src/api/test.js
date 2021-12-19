import request from '@/utils/request'

export function test() {
  console.log('test api start')

  var result = request({
    url: '/licenses',
		 method: 'get',
    params: { }
  })
  console.log(result)

  console.log('test api end ')
  return result
}
