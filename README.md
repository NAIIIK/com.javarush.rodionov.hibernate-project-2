# Hibernate DVD Rental

A Java Hibernate project #2 | JavaRush University

## Stack

| Technology | Version |
|---|---|
| Java | 17+ |
| Hibernate ORM | 7.x |
| Jakarta Persistence | 3.x |
| Database | MySQL / PostgreSQL |
| Lombok | latest |

## DAO Methods

### `CustomerDao`
| Method | Description |
|---|---|
| `createCustomer(firstName, lastName, email, storeId, addressId)` | Creates a new customer linked to an existing store and address |

### `RentalDao`
| Method | Description |
|---|---|
| `rentFilm(customerId, filmId, storeId, staffId, amount)` | Customer visits a store, rents available inventory and makes a payment |
| `returnFilm(rentalId)` | Customer returns a previously rented film (sets `return_date`) |

### `FilmDao`
| Method | Description |
|---|---|
| `releaseFilm(languageId, actorIds, categoryIds, storeIds)` | A new film is released — created with actors/categories and added to each store's inventory |

## Configuration

Edit `src/main/resources/hibernate.properties`: