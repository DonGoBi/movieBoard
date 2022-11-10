function movieCheck(){
	
	if(document.frm.title.value.length==0){
		alert("상품명을 입력해 주세요.")
		frm.title.focus();
		return false;
	}
	
	if(document.frm.price.value.length==0){
		alert("가격을 입력해 주세요.");
		frm.price.focus();
		return false;
	}
	
	if(isNaN(document.frm.price.value)){	//isNaN= 숫자가 아니면 true, 숫자이면 false
		alert("숫자를 입력해 주세요.");
		frm.price.focus();
		return false;
	}
	
	return true;
	
	
	
}