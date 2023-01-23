import { Center } from "./Center";
import { CenterType } from "./typecenter/CenterType";

export class SportCenter extends Center {
    constructor(name: string, address: string, pictures: string[], description: string, centerType: CenterType) {
        super(name, address, description, pictures, centerType)
    }
}