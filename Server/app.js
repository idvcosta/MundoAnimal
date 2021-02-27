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

// Start the server
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`App listening on port ${PORT}`);
  console.log('Press Ctrl+C to quit.');
});
// [END gae_node_request_example]

module.exports = app;
