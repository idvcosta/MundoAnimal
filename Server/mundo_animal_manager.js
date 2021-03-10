//home
function createProduct(id, name, price, image){
	return {
		id:id,
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
		createProduct(10, "Coleira","R$ 15","img/coleira.jpg"), 
		createProduct(1, "Ração para cão","R$ 35","img/racao.jpg"), 
		createService(4,"Banho","R$ 40","img/banhoetosa.png")],
	highlights:[
		createHighlight(HighlightTypes.SALE, "Confira nossas promoções", 1),
		createHighlight(HighlightTypes.NEWS, "Agora fazemos castração", 14),
		createHighlight(HighlightTypes.NEWS, "Chagou a ração premium", 15),
		createHighlight(HighlightTypes.SALE, "Black friday", 5),
		createHighlight(HighlightTypes.EVENT, "Semana da vacina", 5)
	]
};
//home

//products
const productsCategories = [
	{id: 1, name: "Alimentos"},
	{id: 2, name: "Petiscos"},
	{id: 3, name: "Acessórios"},
	{id: 4, name: "Farmácia"}
];

const productsByCategory = {}
productsByCategory[1] = 
{
	name: "Alimentos",
	products: [
		createProduct(1, "Ração para cão", "R$ 35","img/racao.jpg"),
		createProduct(2, "Ração para peixe", "R$ 35", "img/comida_peixe.jpg"),
		createProduct(3, "Ração para gato","R$ 35", "img/racao_gato.jpg"),
		createProduct(4, "Ração para pássaro", "R$ 35","img/racao_passaro.jpg"),
	]
};
productsByCategory[2] = 
{
	name: "Petiscos",
	products: [
	createProduct(5, "Petisco para cão", "R$ 35", "img/petisco_cao.jpg"),
	createProduct(6, "Petisco para gato", "R$ 35", "img/petisco_gato.jpg"),
	createProduct(7, "Petisco para roedores","R$ 35", "img/petisco_roedores.png"),
	]
};
productsByCategory[3] = 
{
	name:"Acessórios",
	products: [
	createProduct(8, "Comedouro", "R$ 35", "img/comedouro_pet.jpeg"),
	createProduct(9, "Roupa", "R$ 35", "img/roupa_pet.jpg"),
	createProduct(10, "Coleira","R$ 15", "img/coleira.jpg"),
	]
};
productsByCategory[4] = 
{
	name: "Farmácia",
	products: [
	createProduct(11, "Antipulgas e carrapatos", "R$ 35", "img/Antipulgas_pet.jpg"),
	createProduct(12, "Suplemento vitamínico", "R$ 35", "img/suplemento_vitaminico_pet.png"),
	createProduct(13, "Shampoo Dermatológico","R$ 15", "img/shampoo_dermatologico.jpg"),
	]
};
//products

//services
function createService(id, name, price, image){
	return {
		id:id,
		name:name, 
		price:price,
		image:image
	};
}

const services = [
	createService(1, "Nutricionista", "R$ 100","img/vet_female.PNG"),
	createService(2, "Veterinário", "R$ 160", "img/vet_male.PNG"),
	createService(3, "Dog Walker", "R$ 50","img/dog_walker.PNG"),
	createService(4, "Banho","R$ 40","img/banhoetosa.png"),
	createService(5, "Escovação de dentes","R$ 30", "img/escovacao_dente.PNG"),
	createService(6, "Aculputura animal", "R$ 60","img/cao_aculputura.jpg"),
	createService(7, "Adestramento", "R$ 100","img/adestramento.PNG"),
	createService(8, "Creche", "R$ 80","img/Creche.PNG"),
	createService(9, "Hotel", "R$ 140","img/Hotel.PNG"),
	createService(10,"Taxi", "R$ 50","img/taxi_pet.PNG")
];

//const servicesCategories = [
//	{id: 1, name: "Serviços médicos"},
//	{id: 2, name: "Estética e bem-estar"},
//	{id: 3, name: "Educação e Transporte"}
//];

//const servicesByCategory = {}
//servicesByCategory[1] = 
//{
//	name: "Serviços médicos",
//	services: [
//		createService(1, "Dra. Rosa", "R$ 150","img/vet_female.PNG"),
//		createService(2, "Dr. John", "R$ 160", "img/vet_male.PNG"),
//		createService(3, "Dra. Maria","R$ 100", "img/vet_female2.PNG"),
//		createService(4, "Dr. Bill", "R$ 35","img/vet_male2.PNG")
//	]
//};
//servicesByCategory[2] = 
//{
//	name: "Estética e bem-estar",
//	services: [
//		createService(5, "Dog Walker", "R$ 50","img/dog_walker.PNG"),
//		createService(6, "Banho","R$ 40","img/banhoetosa.png"),
//		createService(7, "Escovação de dentes","R$ 30", "img/escovacao_dente.PNG"),
//		createService(8, "Aculputura animal", "R$ 60","img/cao_aculputura.jpg")
//	]
//};
//servicesByCategory[3] = 
//{
//	name:"Educação e Transporte",
//	services: [
//		createService(9, "Adestramento", "R$ 100","img/adestramento.PNG"),
//		createService(10, "Creche", "R$ 80","img/Creche.PNG"),
//		createService(11, "Hotel", "R$ 140","img/Hotel.PNG"),
//		createService(12, "Taxi", "R$ 50","img/taxi_pet.PNG")
//	]
//};
//services

const manager = {
    createHomeData: function () {
        return home;
    },
	getProductsCategories: function() {
		return productsCategories;
	},
	getProductsByCategory: function(id){
		return productsByCategory[id];
	},
	//getServicesCategories: function(){
	//	return servicesCategories;
	//},
	//getServicesByCategory: function(id){
	//	return servicesByCategory;
	//}
	getServices: function(){
		return services;
	}
	
};

module.exports = manager;
