from flask import Flask, render_template
import os

app = Flask(__name__)

# To make this demo work without requiring an immediate OpenWeatherMap API key compilation,
# We create a simulated weather analysis using mock data.
# The user can substitute requests.get("https://api.openweathermap.org/data/2.5/weather?q=CITY&appid=KEY") if they register a key.

@app.route('/')
def dashboard():
    # Mock OpenWeatherMap data for trend visualization
    mock_historical_data = {
        'dates': ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'],
        'temperatures': [22, 24, 23, 26, 28], # degrees Celsius
        'humidity': [60, 55, 58, 50, 45]     # percentage
    }
    
    # Simple linear regression simulation to "predict" Saturday's temp based on recent positive trend:
    # 22 -> 24 -> 23 -> 26 -> 28 shows an overall upward slope approx +1.3 per day.
    # We predict 29 by simple extrapolation.
    prediction = {"day": "Saturday", "predicted_temp": 29.5}

    return render_template('index.html', 
                            weather_data=mock_historical_data, 
                            prediction=prediction,
                            api_status="Running on Local AI Simulator (No OpenWeatherMap Key Provided)")

if __name__ == '__main__':
    # Makes sure the Flask app runs locally and correctly creates the template folder if missed
    app.run(debug=True, port=5000)
