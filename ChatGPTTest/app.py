import os
from flask import Flask, render_template,request
from anthropic import Anthropic as LLMapi

app = Flask(__name__)
client = LLMapi()

@app.route('/', methods=['GET','POST'])
def index():
    result = None
    
    if request.method == 'POST':
        num1 = float(request.form.get('num1'))
        num2 = float(request.form.get('num2'))
        operation = request.form.get('operation')
    
        match operation:
            case 'add':
                result = num1 + num2
            case 'subtract':
                result = num1 - num2
            case 'multiply':
                result = num1 * num2
            case 'divide':
                if num2 !=0:
                    result = num1/num2
                else:
                    result = 'Error! Cannot divide by 0'
            case _:
                result = 'Invalid operation selected. Please try again!'
    
    return render_template('index.html',result=result)

@app.route('/database', methods=['GET','POST'])
def db():
    llmResponse = None

    if request.method == 'POST':
        chatText = request.form.get("enteredText")
        print(os.environ.get("ANTHROPIC_API_KEY"))

        message = client.messages.create(
            max_tokens = 100,
            messages =[{"role": "user", "content": "What is your dao name?"}],
            model ="claude-3-opus-20240229")
        
        llmResponse = message.content
    return render_template("database.html",llmResponse=llmResponse)   

@app.route('/game')
def game():
    return render_template("htmlGame.html")

if __name__ == "__main__":
    app.run(debug=True)