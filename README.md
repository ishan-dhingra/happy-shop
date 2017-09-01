# Happy Shop - Demo App describing maintainable and testable code architecture for E-Commence App, using tools like MVVM, Dagger 2, RxJava, Retrofit, Realm and TDD

<a href="https://github.com/ishan-dhingra/happy-shop/raw/master/app-debug.apk">Download APK</a>

<b>Screen Shots</b>

<table>
<tr>
<td>
<img src="https://github.com/ishan-dhingra/happy-shop/raw/master/screen-shots/home.png" width="250" height="445"
</td>
<td>
<img src="https://github.com/ishan-dhingra/happy-shop/raw/master/screen-shots/product-list.png" width="250" height="445"/>
</td>
<td>
<img src="https://github.com/ishan-dhingra/happy-shop/raw/master/screen-shots/product-details.png" width="250" height="445"/>
</td>
</tr>
<tr>
<td>
<img src="https://github.com/ishan-dhingra/happy-shop/raw/master/screen-shots/add-to-cart.png" width="250" height="445"
</td>
<td>
<img src="https://github.com/ishan-dhingra/happy-shop/raw/master/screen-shots/sync-error.png" width="250" height="445"/>
</td>
<td>
<img src="https://github.com/ishan-dhingra/happy-shop/raw/master/screen-shots/server-error.png" width="250" height="445"/>
</td>
</tr>
</table>

Requirement check list:

- User will be able to view products from at least 1 category. <b>All Categories Working</b>
- User will be able to view multiple products from a category view. <b>Done</b>
- After selecting a product from the category view, user will be able to view more detailed information of a product in the product view. <b>Done with nice material effect</b>
- User is able to add products to cart. <b>Done - Add/Remove both</b>
- Usage of REST-ful API endpoints to retrieve products information are required. <b>Done</b> 
- Tests are included - <b>Mostly Done</b>
- Support Android 6.0 (Marshmallow) and backwards compatible till at least Android 4.1 (Jelly Bean) - <b>Done</b>
- Ensure minimal steps are required to run this project on other machines. Few basic instructions are welcomed. <b>Below</b>
- Upon completion, push this to a Git repository and share the link <b>Here we are!</b>

Technical approach

- MVVM for clean code
- Repository pattern
- Dependency Inject
- Single source of truth pricple - for offline experince
- Material design

Additional to Requirement 
- Works in both orientation 
- All categories funtional
- Material design
- Graceful error handling

<b>Build Instructions</b>
I've used stable version(2.3) of Android Studio, so opeing in 2.3 or 2.3+ should work out of the box. However I have attached debug build incase any of any issue.

