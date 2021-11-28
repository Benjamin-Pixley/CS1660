FROM python:3
WORKDIR /termproject
COPY /app .
RUN pip install -r requirements.txt
EXPOSE 5000
ENTRYPOINT ["python"]
CMD ["app.py"]