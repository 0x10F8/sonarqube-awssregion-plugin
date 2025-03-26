// A simple TypeScript example with a function and an interface

interface Person {
    name: string;
    age: number;
}

function greet(person: Person): string {
    return `Hello, ${person.name}! You are ${person.age} years old.`;
}

const user: Person = {
    name: "Calum",
    age: 25,
};

console.log(greet(user));

var region = `us-west-2`;
var region2 = "us-east-1";
var region3 = 'us-east-2';