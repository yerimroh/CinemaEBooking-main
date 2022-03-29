
const container = document.querySelector('.hall_container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const seatCount = document.getElementById('seatCount');
const priceTotal = document.getElementById('priceTotal');


const childTicket = document.getElementById('child_ticket');
const adultTicket = document.getElementById('adult_ticket');
const seniorTicket = document.getElementById('senior_ticket');


document.getElementById("checkoutBtn").disabled = true;
populateUI();

// variables to store ticket prices 
let childCount = 0;
let adultCount = 0; //
let seniorCount = 0; //
let ticketCount = 0;

var totalPrice = 0;
var childPrice = 0;
var adultPrice = 0;
var seniorPrice = 0;


// children ticket
childTicket.addEventListener('change', updateChild);
function updateChild(e) {
  childCount = parseInt(e.target.value);
  childPrice = e.target.value * 3; // change value later on
  updatePrice();
}

// adult ticket
adultTicket.addEventListener('change', updateAdult);
function updateAdult(e) {
  adultCount = parseInt(e.target.value);
  adultPrice = e.target.value * 5; // change value later on

  console.log(adultPrice);
  updatePrice();
}

// senior ticket
seniorTicket.addEventListener('change', updateSenior);
function updateSenior(e) {
  seniorCount = parseInt(e.target.value);
  seniorPrice = e.target.value * 4; // change value later on
  updatePrice();
}


// function that updates ticket price 
function updatePrice() {
  ticketCount = childCount + adultCount + seniorCount;
  totalPrice = childPrice + adultPrice + seniorPrice; // add all ticket prices
  priceTotal.textContent = totalPrice; // update ticket Price on Screen
  updateSelectedCount();
}


/*---------------------------------------------------------------------*/

// Save selected movie index and price
function setMovieData(movieIndex, moviePrice) {
    localStorage.setItem('selectedMovieIndex', movieIndex);
    localStorage.setItem('selectedMoviePrice', moviePrice);
  }
  
  


// Update total and count
function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll('.row .seat.selected');
  const seatsIndex = [...selectedSeats].map(seat => [...seats].indexOf(seat));

  localStorage.setItem('selectedSeats', JSON.stringify(seatsIndex));

  const selectedSeatsCount = selectedSeats.length;
  seatCount.textContent = selectedSeatsCount;

  //console.log(ticketCount);
  //console.log(selectedSeatsCount);
  if(ticketCount > 0 && (selectedSeatsCount == ticketCount)) {
    document.getElementById("checkoutBtn").disabled = false;
  } else {
    document.getElementById("checkoutBtn").disabled = true;
  }
}



// Get data from localstorage and populate UI
function populateUI() {
  const selectedSeats = JSON.parse(localStorage.getItem('selectedSeats'));


  if (selectedSeats !== null && selectedSeats.length > 0) {
    seats.forEach((seat, index) => {
      if (selectedSeats.indexOf(index) > -1) {
        seat.classList.add('selected');
      }
    });
  }

  const selectedMovieIndex = localStorage.getItem('selectedMovieIndex');
  if (selectedMovieIndex !== null) {
    movieSelect.selectedIndex = selectedMovieIndex;
  }
}


// Available seat selecting
container.addEventListener('click', e => {
  if ((e.target.classList.contains('seat') && !e.target.classList.contains('occupied')))
   {
    e.target.classList.toggle('selected');
    updateSelectedCount();
    updatePrice();
  }
});


// Selected seat back to available seat
container.addEventListener('click', e => {
    if (
      e.target.classList.contains('selected')) {
      e.target.classList.id = 'seat';
      updateSelectedCount();
      updatePrice();
    }
  });

// Initial count and total set
updateSelectedCount();
updatePrice();

//_________________________________________________________________________________________

function valueSender() {
  // store each prices in localStorage

  localStorage.setItem('childCount', childCount);
  localStorage.setItem('adultCount', adultCount);
  localStorage.setItem('seniorCount', seniorCount);

  localStorage.setItem('childPrice', childPrice);
  localStorage.setItem('adultPrice', adultPrice);
  

  localStorage.setItem('seniorPrice', seniorPrice);


  //pass to order_summary.html
  window.location.href = "./order_summary.html";



}
