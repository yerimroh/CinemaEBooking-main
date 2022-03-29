const date = document.getElementById('movie_date');
const time = document.getElementById('movie_time');
// retrieve the two select options 


let date_selected = null;
let time_selected = null;

// update the btn only when both options are selected 
function updateBtn() {


    //라인이어딧다고 
    if(date_selected !== null && time_selected !== null) {
        document.getElementById("select_seats_Btn").disabled = false;
    } else {
        document.getElementById("select_seats_Btn").disabled = true;
    } // if-else 

}  //updateBtn




date.addEventListener('change', e => {
    console.log(e.target.value);
   
    date_selected = e.target.value; // date gets updated
    time_selected = null;  // time selected gets resetted to default 

    document.getElementById("movie_time").disabled = false;

    updateBtn();
});



time.addEventListener('change', e => {
    time_selected = e.target.value;
    console.log(e.target.value);

    updateBtn();
});




// function calls
updateBtn(); // update
