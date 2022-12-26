import { Center } from "./Center";

export class SportCenter extends Center {
    constructor(name: string, address: string, pictures: string[], description: string) {
        super(name, address, description, pictures)
    }
}