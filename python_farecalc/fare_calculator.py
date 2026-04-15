def calculate_fare(km, vehicle_type, hour):
    # Dictionary for vehicle pricing per km
    base_fares = {
        "Auto": 15,
        "Mini": 20,
        "Sedan": 25,
        "SUV": 35
    }

    # Handle invalid vehicle types
    if vehicle_type not in base_fares:
        return None, "Invalid vehicle type. Available types: Auto, Mini, Sedan, SUV."

    # Validate distance and hour
    if km < 0:
        return None, "Distance cannot be negative."
    if not (0 <= hour <= 23):
        return None, "Hour must be between 0 and 23."

    # Base cost calculation
    rate_per_km = base_fares[vehicle_type]
    total_fare = km * rate_per_km

    # Surge pricing (5PM–8PM → 17:00 - 20:00)
    is_surge = 17 <= hour <= 20
    if is_surge:
        total_fare *= 1.5

    return total_fare, None

def print_receipt(km, vehicle_type, hour, total_fare):
    print("\n" + "="*30)
    print("      FARECALC RECEIPT")
    print("="*30)
    print(f"Distance:     {km:.2f} km")
    print(f"Vehicle:      {vehicle_type}")
    print(f"Time:         {hour:02d}:00")
    if 17 <= hour <= 20:
        print("Note:         Surge Pricing Applied (1.5x)")
    print("-" * 30)
    print(f"TOTAL FARE:   ₹{total_fare:.2f}")
    print("="*30 + "\n")

if __name__ == "__main__":
    print("Welcome to FareCalc Travel Optimizer")
    try:
        km = float(input("Enter distance in km: "))
        vehicle_type = input("Enter vehicle type (Auto/Mini/Sedan/SUV): ")
        hour = int(input("Enter hour of travel (0-23): "))
        
        fare, error = calculate_fare(km, vehicle_type.strip().title(), hour)
        
        if error:
            print(f"Error: {error}")
        else:
            print_receipt(km, vehicle_type.strip().title(), hour, fare)
            
    except ValueError:
        print("Invalid input. Please enter numbers for distance and hour.")
