/**
 * 
 */
 const nav=document.querySelector('.')
 fetch('navbar.html')
 .then(res=>res.text())
.then(data=>{
	nav.innerHTML=data;
})
 