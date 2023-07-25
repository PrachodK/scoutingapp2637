#!/usr/bin/bash

sudo su

echo "client_max_body_size 50M;" > /etc/nginx/conf.d/elasticbeanstalk/client_max_body_size.conf
/bin/systemctl reload nginx.service
