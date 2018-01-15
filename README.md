# Bookfinder

[![Scrutinizer Build](https://img.shields.io/scrutinizer/build/g/filp/whoops.svg?style=flat-square)]()
[![Platform](https://img.shields.io/badge/platform-Android-green.svg?style=flat-square)]()

### What is Bookfinder?

Bookfinder is an Android application for find books. To that, the application use an API to return a book list that was searched by name, author name or year.

For this ware used:
- MVP multilayer architecture;
- API https://openlibrary.org/dev/docs/api/search ;
- Repository pattern to save liked books in SQLite Database.

## MVP - Model-View-Presenter

First, do not assume that MVP exists due to a necessary adaptation of the MVC to Android, since the MVP is much earlier than Android, more precisely, in the 90's, where the union Apple, HP and IBM did with this pattern of architecture emerged due to needs that the MVC did not cover.

MVP allows a better division in layers when the subject is to isolate upper layers of lower layers, more precisely, to allow the layer directly related to the user interface only to communicate (be dependent) with the layer directly below it, here the layer Presenter. Following diagram:

[![Scrutinizer Build](
http://3.bp.blogspot.com/-3wxJMrBkepU/T4DD-rlKIDI/AAAAAAAAAHw/kv2uJ8kGepI/s1600/mvp-pattern.jpg)]()

Following the definition of the layers:

 - View: As in the MVC, it responds to output and data input, however the output comes from Presenter, input usually comes from the user;
 - Presenter: Layer responsible for responding to the invocations of the preview layer and invocations of the model layer, as well as being able to invoke both layers.Presenter works the formatting of the data that enters both parallel layers and may also include some business logic that some programmers may think should be only in the model layer;
 - Model: The data supply layer in addition to containing the business logic of the problem domain.

##  Repository Pattern 
> A Repository mediates between the domain and data mapping layers, acting like an in-memory domain object collection. Client objects construct query specifications declaratively and submit them to Repository for satisfaction. Objects can be added to and removed from the Repository, as they can from a simple collection of objects, and the mapping code encapsulated by the Repository will carry out the appropriate operations behind the scenes

references: http://martinfowler.com/eaaCatalog/repository.html
[![Scrutinizer Build](
https://image.slidesharecdn.com/th26-copy-111022031205-phpapp01/95/building-high-quality-solutions-with-design-patterns-application-foundations-for-sharepoint-2010-6-728.jpg?cb=1319253156)]()

## Requirements

- Android 5.0 or higher
- Internet access
