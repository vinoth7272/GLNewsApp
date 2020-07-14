# GLNewsApp
This app has been created for viewing a top headline new from the NewsApi org. The UI screen will display the news list and detailed news view.
App:

The app has been designed with the MVVM architecture pattern. The app displays the list of top news in the recycler view by fetches the JSON data from the server using the Retrofit library and converts the JSON into POJO class by using the GSON library. The recycler list can works like lazy loader which means whenever the scroll reaches end of the list item it will fetch the next set of data from API. A dependency injection (Koin library) has been used to create the Retrofit instance.
