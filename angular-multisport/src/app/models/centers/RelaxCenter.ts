import { Comment } from "../page/Comment";
import { Center } from "./Center";
import { CenterType } from "./typecenter/CenterType";

export class RelaxCenter extends Center {
    private services: string[];
    
    constructor(
        id: number,
        name: string,
        address: string,
        pictures: string[],
        description: string,
        centerType: CenterType,
        services: string[],
        rating: number,
        comments: Comment[]) {
        
            super(id, name, address, description, pictures, centerType, rating, comments)
            this.services = services
    
    }

    getService(): string[] {
        return this.services
    }
}