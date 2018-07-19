function templateParserAfter(templateId, data, output){
	var parser = Handlebars.compile( $(templateId).text());
	var resultHTML = parser(data);

	$(output).after(function(){
		return resultHTML; 
	});

}