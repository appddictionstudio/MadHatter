import { SubmittedAtt } from './SubmittedAtt';

export class Attachments {
  id: number;
  attachmentId: number;
  fileNm: string;
  fileSz: string;
  description: String;
  topic: any;
  subAttachments: SubmittedAtt[];
}
