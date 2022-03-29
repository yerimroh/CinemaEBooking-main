// retrieve variables from select_seat.html
var childCount = localStorage.getItem("childCount");
var adultCount = localStorage.getItem("adultCount");
var seniorCount = localStorage.getItem("seniorCount");

var childPrice = parseInt(localStorage.getItem("childPrice"));
var adultPrice = parseInt(localStorage.getItem("adultPrice"));
var seniorPrice = parseInt(localStorage.getItem("seniorPrice"));


// retrieve elements using element id
const child_Count = document.getElementById('child_Count');
const child_Price = document.getElementById('child_Price');
const adult_Count = document.getElementById('adult_Count');
const adult_Price = document.getElementById('adult_Price');
const senior_Count = document.getElementById('senior_Count');
const senior_Price = document.getElementById('senior_Price');
const total_Price = document.getElementById('total_Price');
const sales_Tax = document.getElementById('sales_Tax');

var totalPrice = 0;


// change online fee depending on the movie
var onlineFee = 5;



// this function updates the text fields on the html page
function updateUI() {

    if(childCount == null || childCount == 0) {
        childPrice = 0;
    } 


    if(adultCount == null || adultCount == 0) {
        adultPrice = 0;
    } 


    if(seniorCount == null || seniorCount == 0) {
        seniorPrice = 0;
    } 


    child_Count.textContent = childCount;

    child_Price.textContent = childPrice;

    adult_Count.textContent = adultCount;

    adult_Price.textContent = adultPrice;

    senior_Count.textContent = seniorCount;
    
    senior_Price.textContent = seniorPrice;

    // online fee added 
    total_Price.textContent = totalPrice;
} // updateUI

// this function calculates the total price by adding all the prices
function calculateTotalPrice() {

    totalPrice = childPrice + adultPrice + seniorPrice;

    var salesTax = percentage(totalPrice, 4); // sales tax in GA is 4%
    sales_Tax.textContent = salesTax;

    totalPrice += (salesTax + onlineFee);



    updateUI();
}



function percentage(num, percentage)
{
  return (num / 100) * percentage;
}
          



// call the function to calculate required numbers & update the numbers 
calculateTotalPrice();
