// index.js
const express = require('express');
const app = express();
const cors = require('cors')
const mongoose = require('mongoose')
const Mockaroo = require('mockaroo')


const db = require('./config/keys').mongoURI



var client = new Mockaroo.Client({
  apiKey: '36b160b0' // see http://mockaroo.com/api/docs to get your api key
})

client.generate({
count: 100,
schema: 'books'
}).then(function(records) {
for (var i=0; i<records.length; i++) {
var record = records[i];
console.log('record ' + i, 'Book name:' + record.name + ', Book availability:' + record.availability);
}
}).catch(function(error) {
if (error instanceof Mockaroo.errors.InvalidApiKeyError) {
console.log('invalid api key');
} else if (error instanceof Mockaroo.errors.UsageLimitExceededError) {
console.log('usage limit exceeded');
} else if (error instanceof Mockaroo.errors.ApiError) {
console.log('api error: ' + error.message);
} else {
console.log('unknown error: ' + error);
}
});

mongoose
  .connect(db, { useNewUrlParser: true, useFindAndModify: false, useCreateIndex: true })
  .then(() => console.log('MongoDB Connected'))
  .catch(err => console.log(err))



app.get('/', (req, res) => { // new
  res.send('Homepage! Hello world.');
});








const student = require('./routers/api/student-routes')

app.use(express.json())
app.use(express.urlencoded({ extended: false }))
app.use(cors())

app.use('/api/student-routes', student)

app.use((req, res) => {
  res.status(404).send({ err: 'We can not find what you are looking for' })
})

const port = process.env.PORT || 3000

app.listen(port, () =>
  console.log(`Server is up and running on server ${port}`)
)