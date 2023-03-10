import { Comment } from "../page/Comment";
import { Center } from "./Center";
import { CenterType } from "./typecenter/CenterType";

export class SportCenter extends Center {
    constructor(id: number, name: string, address: string, pictures: string[], description: string, centerType: CenterType, rating: number, comments: Comment[]) {
        super(id, name, address, description, pictures, centerType, rating, comments)
    }
}