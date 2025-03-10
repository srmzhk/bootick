$('#addTrainBtn').on('click', function (event) {
    event.preventDefault();

    const trainDto = {
        id: null,
        number: $('#addTrainNumber').val(),
        price: $('#addTrainPrice').val(),
        seatsAmount: $('#addTrainSeats').val(),
        stops: null,
        seats: null
    };

    $.ajax({
        url: 'http://localhost:8080/trains/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(trainDto),
        success: function (response) {
            alert('Поезд добавлен');
            loadTrains();
        },
        error: function (xhr) {
            alert('Ошибка при добавлении поезда: ' + xhr.responseText);
        }
    });
});

$('#editTrainBtn').on('click', function (event) {
    event.preventDefault();

    const trainDto = {
        id: $('#editTrainId').val(),
        number: $('#editTrainNumber').val(),
        price: $('#editTrainPrice').val(),
        seatsAmount: $('#editTrainSeats').val(),
        seats: null,
        stops: null
    };


    if (!trainDto.id) {
        alert('Пожалуйста, укажите ID поезда для изменения.');
        return;
    }

    $.ajax({
        url: `http://localhost:8080/trains/update`, 
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(trainDto),
        success: function (response) {
            alert('Поезд успешно изменен');
            loadTrains();
        },
        error: function (xhr) {
            alert('Ошибка при изменении поезда: ' + xhr.responseText);
        }
    });
});

$('#addRouteBtn').on('click', function (event) {
    event.preventDefault();
    const routeDto = {
        id: null,
        station: $('#addStation').val(),
        date: $('#addRouteDate').val(),
        time: $('#addRouteTime').val(),
        position: $('#addRoutePosition').val(),
        trainId: $('#addTrainId').val()
    };

    $.ajax({
        url: 'http://localhost:8080/stops/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(routeDto),
        success: function (response) {
            alert('Маршрут добавлен');
            loadRoutes();
        },
        error: function (xhr) {
            alert('Ошибка при добавлении маршрута: ' + xhr.responseText);
        }
    });
});

$('#editRouteBtn').on('click', function (event) {
    event.preventDefault();

    const routeDto = {
        id: $('#editRouteId').val(),
        station: $('#editStation').val(),
        date: $('#editRouteDate').val(),
        time: $('#editRouteTime').val(),
        position: $('#editRoutePosition').val(),
        trainId: $('#editRouteTrainId').val()
    };

    if (!routeDto.id) {
        alert('Пожалуйста, укажите ID маршрута для изменения.');
        return;
    }

    $.ajax({
        url: `http://localhost:8080/stops/update`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(routeDto),
        success: function (response) {
            alert('Маршрут успешно изменен');
            loadRoutes();
        },
        error: function (xhr) {
            alert('Ошибка при изменении маршрута: ' + xhr.responseText);
        }
    });
});

function loadTrains() {
    $.ajax({
        url: 'http://localhost:8080/trains/all',
        method: 'GET',
        success: function (response) {
            let trainTableBody = $('#trainTable tbody');
            trainTableBody.empty();
            response.forEach(train => {
                trainTableBody.append(`
                    <tr>
                        <td>${train.id}</td>
                        <td>${train.number}</td>
                        <td>${train.price}</td>
                        <td>${train.seatsAmount}</td>
                        <td><button class="deleteTrainBtn" data-id="${train.id}">Удалить</button></td>
                    </tr>
                `);
            });

            $('.deleteTrainBtn').on('click', function () {
                const trainId = $(this).data('id');
                deleteTrain(trainId);
            });
        },
        error: function (xhr) {
            alert('Ошибка при загрузке поездов: ' + xhr.responseText);
        }
    });
}

function loadRoutes() {
    $.ajax({
        url: 'http://localhost:8080/stops/all',
        method: 'GET',
        success: function (response) {
            let routeTableBody = $('#routeTable tbody');
            routeTableBody.empty();
            response.forEach(route => {
                routeTableBody.append(`
                    <tr>
                        <td>${route.id}</td>
                        <td>${route.station}</td>
                        <td>${route.date}</td>
                        <td>${route.time}</td>
                        <td>${route.position}</td>
                        <td>${route.trainId}</td>
                        <td><button class="deleteRouteBtn" data-id="${route.id}">Удалить</button></td>
                    </tr>
                `);
            });

            $('.deleteRouteBtn').on('click', function () {
                const routeId = $(this).data('id');
                deleteRoute(routeId);
            });
        },
        error: function (xhr) {
            alert('Ошибка при загрузке маршрутов: ' + xhr.responseText);
        }
    });
}

function deleteTrain(trainId) {
    $.ajax({
        url: `http://localhost:8080/trains/${trainId}`,
        method: 'DELETE',
        success: function (response) {
            alert('Поезд удален');
            loadTrains();
            loadRoutes();
        },
        error: function (xhr) {
            alert('Ошибка при удалении поезда: ' + xhr.responseText);
        }
    });
}

function deleteRoute(routeId) {
    $.ajax({
        url: `http://localhost:8080/stops/${routeId}`,
        method: 'DELETE',
        success: function (response) {
            alert('Маршрут удален');
            loadTrains();
            loadRoutes();
        },
        error: function (xhr) {
            alert('Ошибка при удалении маршрута: ' + xhr.responseText);
        }
    });
}

loadTrains();
loadRoutes();