export class Lesson {
    name: string;

    deserialize(input: any): this {
        return Object.assign(this, input);
    }
}