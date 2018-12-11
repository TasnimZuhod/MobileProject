<?php

if(isset($_POST['price_value'])){
	
	$price = $_POST['price_value'];
	
	$price = $price * 3.75;
	
	echo(json_encode($price));
	
}
 
?>