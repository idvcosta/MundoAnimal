'use strict';

const express = require('express');
const manager = require('./mundo_animal_manager');
const app = express();

app.use(express.static('public'));

app.get('/', (req, res) => {
    res.send('Hello World!')
});

app.get('/home', (req, res) => {
    res.send(manager.createHomeData());
});

//productsCategories
app.get('/productsCategories', (req,res) => {
	res.send(manager.getProductsCategories());
});

//productsByCategory?categoryId=10
app.get('/productsByCategory', (req,res) => {
	var id = req.query.categoryId;
	res.send(manager.getProductsByCategory(id));
});

//product?id=4172
app.get('/product', (req,res) => {
	var id = req.query.id;
	res.send(manager.getProduct(id));
});

//servicesCategories
//app.get('/servicesCategories', (req,res) =>{
//	res.send(manager.getServicesCategories());
//});

//servicesByCategory?categoryId=10
//app.get('/servicesByCategory', (req,res) => {
//	var id = req.query.categoryId;
//	res.send(manager.getServicesByCategory(id));
//});

//servico
app.get('/servicos', (req,res) => {
	res.send(manager.getServices());
});

// Start the server
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`App listening on port ${PORT}`);
  console.log('Press Ctrl+C to quit.');
});
// [END gae_node_request_example]

module.exports = app;
