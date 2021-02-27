function createProduct(name, price, image){
	return {
		name:name, 
		price:price,
		image:image
	};
}

function createHighlight( type, title, id){
	return {
		id:id,
		type:type,
		title:title		
	};
}

const HighlightTypes = {
	NEWS:1,
	SALE:2,
	EVENT:3
}
const home = {
	mostWanted: [
		createProduct("Coleira","R$ 15","img/coleira.jpg"), 
		createProduct("Ração","R$ 35","img/racao.jpg"), 
		createProduct("Banho","R$ 40","img/banhoetosa.png")],
	highlights:[
		createHighlight(HighlightTypes.SALE, "Confira nossas promoções", 1),
		createHighlight(HighlightTypes.NEWS, "Agora fazemos castração", 14),
		createHighlight(HighlightTypes.NEWS, "Chagou a ração premium", 15),
		createHighlight(HighlightTypes.SALE, "Black friday", 5),
		createHighlight(HighlightTypes.EVENT, "Semana da vacina", 5)
	]
};
const manager = {
    createHomeData: function () {
        return home;
    }
};

module.exports = manager;
