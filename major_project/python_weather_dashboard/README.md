# Python Weather Data Dashboard

An industry-level Web App Dashboard built natively with Python's Flask library and HTML/Canvas Chart JS.

## Features
- **Web UI & Routing**: Instead of CLI execution, this hosts an active Web Server on `localhost:5000` via Flask routing.
- **Machine Learning (Regression Simulation)**: Aggregates historical metrics to construct a functional pseudo-algorithm prediction modeling behavior of temperatures. 
- **Chart.js Integration**: Transmits python values direct to the DOM payload utilizing JavaScript API visualizations, rendering robust Temperature vs Humidity models.
- **API Simulation Mode**: If an authentic API Key is missing, it dynamically fails down gracefully to a Local AI simulation mode preventing unhandled crashes while still fully executing the demonstration.

## How to Deploy
Ensure you install Flask.
```bash
pip install flask
```

Startup the python backend script:
```bash
python app.py
```
Open a browser and navigate to `http://127.0.0.1:5000/`.
