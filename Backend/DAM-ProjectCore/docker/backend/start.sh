#!/bin/bash

pip install --upgrade pip
pip install -r /app/requirements.txt

export PYTHONPATH=$PYTHONPATH:/app
python /app/dev/reset_database.py

gunicorn -b [::]:8000 app:app --reload
