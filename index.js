// index.js
const express = require('express');
const app = express();
const cors = require('cors')
const mongoose = require('mongoose')



const db = require('./config/keys').mongoURI

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