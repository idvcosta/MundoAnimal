function createProduct(name,price){
	return {
		name:name, 
		price:price
	};
}
var home = {
	products: [
		createProduct("Coleira","R$ 15"), 
		createProduct("Ração","R$ 35"), 
		createProduct("Banho","R$ 40")]
};
var manager = {
    createHomeData: function () {
        return home;
    }
};

module.exports = manager;
