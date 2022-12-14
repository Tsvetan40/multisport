import { Center } from "./Center";

export class RelaxCenter extends Center {
    private services: string[];
    
    constructor(services: string[], name: string, address: string, description: string) {
        super(name, address, description)
        this.services = services
    }
}