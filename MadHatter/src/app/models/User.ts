import { UserOrgRole } from "./UserOrgRole";
import { Department } from "./Department";
import { Organization } from "./Organization";
import { Person } from "./Person";

export class User {
  id: number;
  person: Person;
  userNm: string;
  userOrgRoles: UserOrgRole[];
  department: Department;
  organization: Organization;
}
