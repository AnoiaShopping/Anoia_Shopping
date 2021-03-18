FROM python:3.7

COPY start.sh /scripts/start.sh
RUN chmod +x /scripts/start.sh
RUN mkdir /logs
WORKDIR /app
EXPOSE 8000
ENTRYPOINT ["/scripts/start.sh"]