# gatewaydemo


# 解决上传代码到GitHub报错 Push rejected: Push to origin/master was rejected
git pull origin master --allow-unrelated-histories



# 测试

curl -XGET -H 'Accept:application/json' http://192.168.10.102:8010/user/get/123?t=2132323


curl http://192.168.10.120:18080/api/user/user/get/123 -i


# 调用consumer
curl http://192.168.10.120:18080/api/consumer/remote/feign/user/123 -i