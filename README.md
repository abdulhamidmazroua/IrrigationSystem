<<<<<<< HEAD
# Irrigation System REST API

## Introduction:

=======
# Automatic Irrigation system REST API

## Plot of Land Endpoints:

### Add new plot of land:
    This endpoint allows the user to add a new plot of land to the system.
    
    HTTP Method: POST
    Endpoint: /plots
    Request body: JSON object representing the plot of land
    Response: HTTP status code indicating success or failure
    Example of usage:
        Request: POST /plots
        RequestBody(application/json):
            {
                "name": "plot D",
                "area": 2500
            }

### Configure a plot of land: 
    This endpoint enables the user to configure the irrigation time slots and water amount for a specific plot of land.
    
    HTTP Method: POST
    Endpoint: /plots/{plotId}/configurations
    Request body: JSON object representing the configuration
    Response: HTTP status code indicating success or failure
    Example of usage:
        Request: POST /plots
        RequestBody(application/json):
        {
            "waterRequired": 1000.0,
            "timeSlot": 
            {
                "startTime": "14:00:00",
                "endTime": "15:30:00",
                "status": "Reserved"
            }
        }

### Edit a plot of land: 
    This endpoint allows the user to update the details of a specific plot of land.
    
    HTTP Method: PUT
    Endpoint: /plots/{plotId}
    Request body: JSON object representing the updated plot of land
    Response: HTTP status code indicating success or failure
    Example of usage:
        Request: POST /plots
        RequestBody(application/json):
            {
                "name": "plot D Updated",
                "area": 5000
            }

### List all plots and their details: 
    This endpoint retrieves a list of all plots of land and their associated configurations.
    
    HTTP Method: GET
    Endpoint: /plots
    Response: JSON array containing the list of plots of land with their configurations
    Example of usage:
        Request: GET /plots

## Automatic Irrigation System Endpoints:

### Integration interface with the sensor device:
    This endpoint simulates the integration with the sensor device to initiate irrigation for a plot of land.
    
    HTTP Method: POST
    Endpoint: /plots/{plotId}/irrigate
    Response: HTTP status code indicating success or failure
    Example of usage (for an active sensor):
        Request: GET /plots/1/irrigate
    Example of usage (for an inactive sensor):
        Request: GET /plots/2/irrigate

### Update the status of the slot:
    This endpoint updates the status of the irrigation slot once the request is successfully sent to the sensor device and it is requested automatically.

### Retry calls to the sensor device:
    This functionality is automatically triggered in case the sensor device is not available during the initial request.
    
    This logic should be implemented in the background and handled internally without the need for a specific REST endpoint.

## Alerting System Endpoint:
    
### Alerting system: 
    This functionality is responsible for sending alerts if the sensor device is not available and after exceeding the retry times.
    This logic should also be implemented in the background and handled internally without the need for a specific REST endpoint.
