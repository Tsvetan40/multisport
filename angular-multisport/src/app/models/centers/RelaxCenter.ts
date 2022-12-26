import { Center } from "./Center";

export class RelaxCenter extends Center {
    private services: string[];
    
    constructor(services: string[], name: string, address: string, pictures: string[], description: string) {
        super(name, address, description, pictures)
        this.services = services
    }
}