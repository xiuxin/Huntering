$(document).ready(function(){
	$(".feedback_desb").each(function(index, el){
		$(el).click(function(){
			console.log(el);
			var currentDetail = $("#feedback_detail").val();
			if(currentDetail != "") {
				currentDetail = currentDetail + ", ";
			} 
			$("#feedback_detail").val(currentDetail + $(el).text());
			el.innerText
		});
	});
	
});