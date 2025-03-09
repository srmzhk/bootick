function initSelect2(selector, placeholderText) {
    $(selector).select2({
        ajax: {
            url: function (params) {
                var formattedTerm = params.term.charAt(0).toUpperCase() + params.term.slice(1).toLowerCase();
                return 'http://localhost:8080/stops/search?symbols=' + formattedTerm;
            },
            dataType: 'json',
            delay: 250,
            processResults: function (data) {
                return {
                    results: data.map(function(item) {
                        return {
                            id: item,
                            text: item 
                        };
                    })
                };
            },
            cache: true
        },
        minimumInputLength: 1,
        placeholder: placeholderText,
        allowClear: true
    });
}

function renderTrainCards(trains) {
    var container = $('#train-list');
    container.empty();

    trains.forEach(train => {
        var card = $('#train-template').clone();
        card.removeAttr('id');

        card.find('.train-number').text('№' + train.number);

        var travelTime = train.travelTime;
        var hours = Math.floor(travelTime / 60);
        var minutes = travelTime % 60;
        var fromTime = train.fromStop.time;
        var toTime = train.toStop.time;

        card.find('.time-from h2').text(fromTime);
        card.find('.time-from p').text(train.fromStop.station); 
        card.find('.time-to h2').text(toTime);
        card.find('.time-to p').text(train.toStop.station);
        card.find('.time').text(`${hours} ч ${minutes} мин`);

        card.find('.train-seats-amount').text(train.availableSeats);
        card.find('.train-price').text(train.price + ' BYN');

        card.data('train-id', train.trainId);
        card.data('from-stop-id', train.fromStop.id);
        card.data('to-stop-id', train.toStop.id);
        card.data('seat-id', train.seatId);
        card.data('available-seats', train.availableSeats);

        card.find('.order-btn').on('click', function() {
            const availableSeats = card.data('available-seats');

            if (availableSeats === 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'Мест нет!',
                    text: 'На этот поезд больше нет свободных мест.',
                    confirmButtonText: 'Ок',
                    customClass: {
                        confirmButton: 'custom-confirm-btn'
                    }
                });
                return;
            }

            Swal.fire({
                title: 'Введите номер телефона',
                input: 'tel',
                inputPlaceholder: '+375XXXXXXXXX',
                inputAttributes: {
                    maxlength: '13',
                    autocapitalize: 'off',
                    autocorrect: 'off'
                },
                showCancelButton: true,
                confirmButtonText: 'Забронировать',
                cancelButtonText: 'Отмена',
                customClass: {
                    confirmButton: 'custom-confirm-btn',
                    cancelButton: 'custom-cancel-btn'
                },
                inputValidator: (value) => {
                    if (!value) {
                        return 'Введите номер телефона!';
                    }

                    const phoneRegex = /^\+375\d{9}$/;
                    if (!phoneRegex.test(value)) {
                        return 'Введите корректный номер в формате +375XXXXXXXXX';
                    }
                }
            }).then((result) => {
                if (result.isConfirmed) {
                    const phone = result.value;

                    const bookingData = {
                        phone: phone,
                        bookingTime: null,
                        trainId: card.data('train-id'),
                        fromStopId: card.data('from-stop-id'),
                        toStopId: card.data('to-stop-id'),
                    };

                    $.ajax({
                        url: 'http://localhost:8080/booking/book',
                        method: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(bookingData),
                        success: function(response) {
                            Swal.fire({
                                title: 'Успех!',
                                text: `Ваш номер брони: ${response.id}`,
                                icon: 'success',
                                confirmButtonText: 'Ок',
                                customClass: {
                                    confirmButton: 'custom-confirm-btn'
                                }
                            });
                        },
                        error: function(xhr) {
                            Swal.fire({
                                title: 'Ошибка!',
                                text: `Ошибка при заказе билета: ${xhr.responseText}`,
                                icon: 'error',
                                confirmButtonText: 'Ок',
                                customClass: {
                                    confirmButton: 'custom-confirm-btn'
                                }
                            });
                        }
                    });
                }
            });
        });

        card.show();
        container.append(card);
    });
}

$(document).ready(function() {
    $('.search-btn').on('click', function() {
        var fromStopData = $('#from').select2('data')[0];
        var toStopData = $('#to').select2('data')[0];
        var fromStop = fromStopData ? fromStopData.text : null;
        var toStop = toStopData ? toStopData.text : null;
        var date = $('#date').val();

        console.log(fromStop);

        if (!fromStop || fromStop.trim() === '') {
            Swal.fire({
                icon: 'error',
                title: 'Ошибка!',
                text: 'Пожалуйста, выберите поле "Откуда".',
                confirmButtonText: 'Ок',
                customClass: {
                    confirmButton: 'custom-confirm-btn'
                }
            });
            return;
        }

        if (!toStop || toStop.trim() === '') {
            Swal.fire({
                icon: 'error',
                title: 'Ошибка!',
                text: 'Пожалуйста, выберите поле "Куда".',
                confirmButtonText: 'Ок',
                customClass: {
                    confirmButton: 'custom-confirm-btn'
                }
            });
            return;
        }

        if (fromStop === toStop) {
            Swal.fire({
                icon: 'error',
                title: 'Ошибка!',
                text: 'Поля "Откуда" и "Куда" не могут совпадать.',
                confirmButtonText: 'Ок',
                customClass: {
                    confirmButton: 'custom-confirm-btn'
                }
            });
            return;
        }

        if (!date) {
            Swal.fire({
                icon: 'error',
                title: 'Ошибка!',
                text: 'Пожалуйста, выберите дату.',
                confirmButtonText: 'Ок',
                customClass: {
                    confirmButton: 'custom-confirm-btn'
                }
            });
            return;
        }

        var searchDto = {
            fromStop: fromStop,
            toStop: toStop,
            date: date
        };

        $.ajax({
            url: 'http://localhost:8080/trains/search',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(searchDto),
            success: function(response) {
                renderTrainCards(response);
            },
            error: function(xhr) {
                var errorResponse = JSON.parse(xhr.responseText); // Преобразуем ответ в объект
        
                if (errorResponse.errorCode === "ITEM_NOT_FOUND") {
                    Swal.fire({
                        icon: 'error',
                        title: 'Нет поездов',
                        text: 'На выбранную дату поездов не найдено.',
                        confirmButtonText: 'Ок',
                        customClass: {
                            confirmButton: 'custom-confirm-btn'
                        }
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Ошибка!',
                        text: 'Произошла ошибка при получении данных.',
                        confirmButtonText: 'Ок',
                        customClass: {
                            confirmButton: 'custom-confirm-btn'
                        }
                    });
                }
            }
        });
    });

    initSelect2('#from', 'Откуда...');
    initSelect2('#to', 'Куда...');
});

$('.profile').on('click', function() {
    Swal.fire({
        title: 'Отмена брони',
        html: `
            <input id="bookingNumber" class="swal2-input" placeholder="Номер брони" type="text">
            <input id="phoneNumber" class="swal2-input" placeholder="Номер телефона" type="text">
        `,
        showCancelButton: true,
        confirmButtonText: 'Отменить',
        cancelButtonText: 'Отмена',
        preConfirm: () => {
            const bookingNumber = document.getElementById('bookingNumber').value;
            const phoneNumber = document.getElementById('phoneNumber').value;

            const bookingNumberPattern = /^\d{1,10}$/;
            if (!bookingNumber || !bookingNumberPattern.test(bookingNumber)) {
                Swal.showValidationMessage('Пожалуйста, введите корректный номер брони (от 1 до 10 цифр)');
                return false;
            }

            const phoneNumberPattern = /^\+375\d{9}$/;
            if (!phoneNumber || !phoneNumberPattern.test(phoneNumber)) {
                Swal.showValidationMessage('Пожалуйста, введите корректный номер телефона в формате +375XXXXXXXXX');
                return false;
            }

            $.ajax({
                url: `http://localhost:8080/booking/cancel?bookingNumber=${encodeURIComponent(bookingNumber)}&phoneNumber=${encodeURIComponent(phoneNumber)}`,
                method: 'DELETE',
                success: function(response) {
                        Swal.fire('Успех!', 'Бронирование успешно отменено.', 'success');
                },
                error: function(xhr) {
                    var errorResponse = JSON.parse(xhr.responseText); 
                    if (errorResponse.errorCode === 'TIME_IS_OVER') {
                        Swal.fire('Ошибка!', 'Отмена невозможна. До отправки поезда осталось меньше часа.', 'error');
                    } else if (errorResponse.errorCode === 'ITEM_NOT_FOUND') {
                        Swal.fire('Ошибка!', 'Бронь не найдена для заданных данных. Пожалуйста, проверьте введённую информацию.', 'error');
                    } else {
                        Swal.fire('Ошибка!', `Не удалось отменить бронирование: ${xhr.responseText}`, 'error');
                    }
                }
            });
        }
    });
});
