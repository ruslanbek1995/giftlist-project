package com.peakosoft.giftlistj7.service;


import com.peakosoft.giftlistj7.model.entities.Booking;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.BookingRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public boolean book(Principal principal, Long bookingId) {
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Проверяем, что бронирование еще не привязано к пользователю
           Optional<Booking> bookingOptional = Optional.ofNullable(bookingRepository.findById(bookingId)
                   .orElseThrow(() -> new EntityNotFoundException("booking whizh id not found")));
            if (bookingOptional.isPresent()) {
                Booking booking = bookingOptional.get();
                if (!user.getBooking().contains(booking)) {
                    user.getBooking().add(booking);
                    booking.setUser(user);
                    // Сохраняем изменения
                    userRepository.save(user);
                    bookingRepository.save(booking);
                    return true;

                }
            }
        }
        return false;

    }
}