import { Gender } from "./gender.enum";
import { Lesson } from "./lesson.model";

export class Teacher {
    firstName: string;
    lastName: string;
    email: string;
    gender: Gender;
    teacherLesson: Lesson;

    fullName(): string {
        return `${this.firstName} ${this.lastName}`;
    }

    deserialize(input: any): this {
        return Object.assign(this, input);
    }
}