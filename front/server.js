const express = require('express');
const app = express();
const PORT = 3000;

const PASSWORD = 'admin';

app.use(express.static('public'));

app.get('/admin', (req, res) => {
    // Получаем пароль из запроса (например, /admin?password=admin)
    const enteredPassword = req.query.password;
    
    if (enteredPassword === PASSWORD) {
        res.sendFile(__dirname + '/public/html/admin.html');
    } else {
        res.status(401).send('Доступ запрещён. Неверный пароль.');
    }
});

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/public/html/index.html');
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});