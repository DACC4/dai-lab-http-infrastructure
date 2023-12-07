FROM nginx:1.25.3-alpine3.18-slim
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY ./sources /var/www/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]