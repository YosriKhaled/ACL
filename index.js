// index.js
const express = require('express');
const app = express();
const cors = require('cors')
const mongoose = require('mongoose')
const Mockaroo = require('mockaroo')
const bookModel = require('./models/book')

const db = require('./config/keys').mongoURI
const apiKey = require('./config/keys').apiKey

mongoose
  .connect(db, { useNewUrlParser: true, useFindAndModify: false, useCreateIndex: true })
  .then(() => console.log('MongoDB Connected'))
  .catch(err => console.log(err))



app.get('/', (req, res) => { // new
  res.send('Homepage! Hello world.');
});




bookModel.deleteMany({}, (err , collection) => {
  if(err) throw err;
  
  
});


var client = new Mockaroo.Client({
  apiKey
})

client.generate({
count: 100,
schema: 'books'
}).then(function(records) {
for (var i=0; i<records.length; i++) {
var record = records[i];
var bookRecord={
  name:record.name,
  availability:record.availability
}
bookModel.create(bookRecord)
//console.log('record ' + i, 'Book name:' + bookRecord.name + ', Book availability:' + bookRecord.availability);
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



const student = require('./routers/api/student-routes')
const book = require('./routers/api/book-routes')

app.use(express.json())
app.use(express.urlencoded({ extended: false }))
app.use(cors())

app.use('/api/student-routes', student)
app.use('/api/book-routes',book)

app.use((req, res) => {
  res.status(404).send({ err: 'We can not find what you are looking for' })
})

const port = 3000

app.listen(port, () =>
  console.log(`Server is up and running on server ${port}`)
)