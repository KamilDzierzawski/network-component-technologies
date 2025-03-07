//package pl.edu.dik.tks.service;
//
//import pl.edu.dik.tks.exception.business.AccountNotActiveException;
//import pl.edu.dik.tks.exception.business.StartDateBeforeEndDateException;
//import pl.edu.dik.tks.model.rent.Rent;
//
//import java.util.UUID;
//
//public class RentService {
//
//    public UUID createRent(Rent rent) throws
//            StartDateBeforeEndDateException,
//            AccountNotActiveException {
//
//        if (!rent.getStartDate().isBefore(rent.getEndDate())) {
//            throw new StartDateBeforeEndDateException("Start date must be before end date.");
//        }
//
//        if (!rent.getAccount().isEnable()) {
//            throw new AccountNotActiveException("Client account is not active.");
//        }
//
////        try (ClientSession session = mongoClient.startSession()) {
////            session.startTransaction();
////
////            if (!clientRepository.markAsRented(session, rent.getUser().getId())) {
////                session.abortTransaction();
////                throw new LogicException("Client is not available for rent or does not exist.");
////            }
////
////            // Validate game
////            boolean gameMarkedAsRented = false;
////            if (rent.getGame() instanceof BoardGame) {
////                gameMarkedAsRented = boardGameRepository.markAsRented(session, rent.getGame().getId());
////            } else if (rent.getGame() instanceof ComputerGame) {
////                gameMarkedAsRented = computerGameRepository.markAsRented(session, rent.getGame().getId());
////            } else {
////                session.abortTransaction();
////                throw new LogicException("Unsupported game type: " + rent.getGame().getClass().getSimpleName());
////            }
////
////            if (!gameMarkedAsRented) {
////                session.abortTransaction();
////                throw new LogicException("Game is not available for rent.");
////            }
////
////            // Calculate rent price
////            rent.setRentalPrice(calculateRentPrice(rent.getStartDate(), rent.getEndDate(), rent.getGame().getPricePerDay()));
////
////            if (rentRepository.insert(session, rent)) {
////                session.commitTransaction();
////                return rent;
////            }
////
////            session.abortTransaction();
////            throw new LogicException("Rent could not be created.");
////        } catch (LogicException e) {
////            throw e;
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////
////            throw new DatabaseException("Database error. + " + e.getMessage());
////        }
//        return UUID.randomUUID();
//    }
//}
