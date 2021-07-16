#!/usr/bin/env bash
printf "show movies after given dates\n"
curl -H "Content-Type:application/json" -X GET 'http://localhost:8080/movies/byScreeningTimeResponse?startDateTime=2021-11-22T10:20&endDateTime=2021-11-22T12:20'
printf "\nget movie - Room name and avilable seats\n"
curl -H "Content-Type:application/json" -X GET http://localhost:8080/movies/1
printf "\npost reservation"
printf "\n1:\n"
curl -H "Content-Type:application/json" -X POST -d '{"firstName":"Adam", "surname":"Smith","movie": {"movieId": 1}, "seats":[{"seatRow":1, "seatColumn":1, "ticketType":"adult"}]}' http://localhost:8080/makeReservation
printf "\n2:\n"
curl -H "Content-Type:application/json" -X POST -d '{"firstName":"John", "surname":"Johnson","movie": {"movieId": 1}, "seats":[{"seatRow":1, "seatColumn":3, "ticketType":"adult"}, {"seatRow":1, "seatColumn":2, "ticketType":"child"}]}' http://localhost:8080/makeReservation
