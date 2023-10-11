
    // Blood Pressure Chart
    let bloodPressureChart = document.getElementById('bpChart').getContext('2d')
    let gradient = bloodPressureChart.createLinearGradient(0, 0, 0, 100)
    gradient.addColorStop(0, "#29a9e1")
    gradient.addColorStop(1, "#2ccec2")

    let chart = new Chart(bloodPressureChart, {
        type: 'line',
        // The data for our dataset
        data: {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "Blood Pressure",
                backgroundColor: gradient,
                borderColor: 'transparent',
                data: [105, 112, 105, 120, 110, 119, 110],
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            elements: {
                point: {
                    radius: 2
                }
            },
            legend: {
                display: false
            },
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        }
                    }
                }],
                yAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        },
                        suggestedMin: 100
                    }
                }]
            }
        }
    })

    // Sleep Chart
    let sleepChartContainer = document.getElementById('sleepChart').getContext('2d')
    let sleepChartGradient = sleepChartContainer.createLinearGradient(0, 0, 400, 0)
    sleepChartGradient.addColorStop(0, "#44D1CA")
    sleepChartGradient.addColorStop(1, "#28A3D0")

    let sleepChartBackgroundGradient = sleepChartContainer.createLinearGradient(0, 0, 400, 0)
    sleepChartBackgroundGradient.addColorStop(0, "rgba(68, 209, 202, 0.1)")
    sleepChartBackgroundGradient.addColorStop(1, "rgba(40, 163, 208, 0.1)")

    let sleepChart = new Chart(sleepChartContainer, {
        type: 'line',
        // The data for our dataset
        data: {
            labels: ["22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "1", "2", "3"],
            datasets: [{
                label: "Hours of sleep",
                backgroundColor: sleepChartBackgroundGradient,
                borderColor: sleepChartGradient,
                data: [7.5, 6.7, 7.8, 8.2, 6.7, 6.5, 8.2, 7.5, 8.2, 6.9, 7.2, 7.5, 6.9],
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            elements: {
                point: {
                    radius: 2
                }
            },
            legend: {
                display: false
            },
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: true,
                        color: "#F3F4F4"
                    },
                    ticks: {
                        fontColor: "#BDC3C6"
                    }
                }],
                yAxes: [{
                    maxBarThickness: 1,
                    gridLines: {
                        drawBorder: true,
                        display: false,
                        color: "#F3F4F4"
                    },
                    ticks: {
                        callback: function(value, index, values) {
                            return null
                        },
                        suggestedMin: 6
                    }
                }]
            }
        }
    })

    // Temperature Chart
    let temperatureChartContainer = document.getElementById('temperatureGraph').getContext('2d')
    let temperatureGradient = temperatureChartContainer.createLinearGradient(0, 0, 100, 0)
    temperatureGradient.addColorStop(0, "#feb35e")
    temperatureGradient.addColorStop(1, "#fe8550")
    let temperatureChart = new Chart(temperatureChartContainer, {
        type: 'line',
        // The data for our dataset
        data: {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "My First dataset",
                backgroundColor: gradient,
                borderColor: temperatureGradient,
                fill: false,
                data: [105, 108, 105, 110, 108, 105, 110],
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            tooltips: {
                enabled: true
            },
            elements: {
                point: {
                    radius: 0
                }
            },
            legend: {
                display: false
            },
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        }
                    }
                }],
                yAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        },
                        suggestedMin: 100
                    }
                }]
            }
        }
    })

    // Calorie Chart
    let calorieChartContainer = document.getElementById('calorieGraph').getContext('2d')
    let calorieChartGradient = calorieChartContainer.createLinearGradient(0, 0, 100, 0)
    calorieChartGradient.addColorStop(0, "#03d7a6")
    calorieChartGradient.addColorStop(1, "#22b6d1")
    let calorieChart = new Chart(calorieChartContainer, {
        type: 'line',
        // The data for our dataset
        data: {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "My First dataset",
                backgroundColor: gradient,
                borderColor: calorieChartGradient,
                fill: false,
                data: [105, 108, 115, 105, 110, 112, 109],
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            tooltips: {
                enabled: true
            },
            elements: {
                point: {
                    radius: 0
                }
            },
            legend: {
                display: false
            },
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        }
                    }
                }],
                yAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        },
                        suggestedMin: 100
                    }
                }]
            }
        }
    })

    // Heart Rate Chart
    let heartRateChartContainer = document.getElementById('heartRateGraph').getContext('2d')
    let heartRateChartGradient = heartRateChartContainer.createLinearGradient(0, 0, 100, 0)
    heartRateChartGradient.addColorStop(0, "#fd6eb5")
    heartRateChartGradient.addColorStop(1, "#ca65ff")
    let heartRateChart = new Chart(heartRateChartContainer, {
        type: 'line',
        // The data for our dataset
        data: {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "My First dataset",
                backgroundColor: gradient,
                borderColor: heartRateChartGradient,
                fill: false,
                data: [105, 108, 109, 115, 108, 105, 104],
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            tooltips: {
                enabled: true
            },
            elements: {
                point: {
                    radius: 0
                }
            },
            legend: {
                display: false
            },
            layout: {
                padding: {
                    left: 0,
                    right: 0,
                    top: 0,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        }
                    }
                }],
                yAxes: [{
                    maxBarThickness: 0,
                    gridLines: {
                        drawBorder: false,
                        display: false
                    },
                    ticks: {
                        callback: function(vlaue, index, values) {
                            return null
                        },
                        suggestedMin: 100
                    }
                }]
            }
        }
    })

    // Current Position Helper
    function getLocation() {
        return new Promise((resolve, reject) => {
            try {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function(position) {
                        resolve(position.coords)
                    })
                } else {
                    throw Error('Geolocation disabled.')
                }
            } catch (err) {
                console.log(err)
                reject(err)
            }
        })
    }

    document.addEventListener("DOMContentLoaded", function() {
        getLocation()
            .then((coords) => {
                googleMapsInit(coords.longitude, coords.latitude)
            })
            .catch((err) => {
                console.log(err)
                googleMapsInit()
            })
    })

    // Initiate Google Maps render
    function googleMapsInit(longitude, latitude) {
        let map = document.getElementById('map')
            // Default Lon/Lat to UK if geolocation is disabled 
        if (!longitude || !latitude) {
            let longitude = 3.4360
            let latitude = 55.3781
        }
        map = new google.maps.Map(document.getElementById('map'), {
            center: {
                lat: latitude,
                lng: longitude
            },
            zoom: 7,
            disableDefaultUI: true,
            styles: [{
                "elementType": "geometry",
                "stylers": [{
                    "color": "#f5f5f5"
                }]
            }, {
                "elementType": "geometry.fill",
                "stylers": [{
                    "color": "#ffffff"
                }]
            }, {
                "elementType": "labels",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "elementType": "labels.icon",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#616161"
                }]
            }, {
                "elementType": "labels.text.stroke",
                "stylers": [{
                    "color": "#f5f5f5"
                }]
            }, {
                "featureType": "administrative.land_parcel",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "administrative.land_parcel",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#bdbdbd"
                }]
            }, {
                "featureType": "administrative.neighborhood",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "poi",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#eeeeee"
                }]
            }, {
                "featureType": "poi",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#757575"
                }]
            }, {
                "featureType": "poi.business",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "poi.park",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#e5e5e5"
                }]
            }, {
                "featureType": "poi.park",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#9e9e9e"
                }]
            }, {
                "featureType": "road",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#ffffff"
                }]
            }, {
                "featureType": "road",
                "elementType": "labels.icon",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "road.arterial",
                "elementType": "labels",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "road.arterial",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#757575"
                }]
            }, {
                "featureType": "road.highway",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#dadada"
                }]
            }, {
                "featureType": "road.highway",
                "elementType": "labels",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "road.highway",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#616161"
                }]
            }, {
                "featureType": "road.local",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "road.local",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#9e9e9e"
                }]
            }, {
                "featureType": "transit",
                "stylers": [{
                    "visibility": "off"
                }]
            }, {
                "featureType": "transit.line",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#e5e5e5"
                }]
            }, {
                "featureType": "transit.station",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#eeeeee"
                }]
            }, {
                "featureType": "water",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#c9c9c9"
                }]
            }, {
                "featureType": "water",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#9e9e9e"
                }]
            }]
        })
    }
