from flask import Flask, render_template,request

app = Flask(__name__)

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
   

if __name__ == "__main__":
    app.run(debug=True)