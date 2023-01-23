import { Center } from "./Center";
import { CenterType } from "./typecenter/CenterType";

export class RelaxCenter extends Center {
    private services: string[];
    
    constructor(name: string,
        address: string,
        pictures: string[],
        description: string,
        centerType: CenterType,
        services: string[]) {
        
            super(name, address, description, pictures, centerType)
            this.services = services
    
    }

    getService(): string[] {
        return this.services
    }
}