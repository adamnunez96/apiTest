docker run -d \
 --name=@project.artifactId@ \
 --hostname=@project.groupId@ \
 --domainname=test.com.py \
 --log-driver json-file --log-opt max-size=10m --log-opt max-file=14 \
 -p 8080:8080 \
 @project.groupId@/@project.artifactId@:@project.version@