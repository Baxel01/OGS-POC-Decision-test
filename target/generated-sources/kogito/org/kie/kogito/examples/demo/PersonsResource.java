/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.examples.demo;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jbpm.util.JsonSchemaUtil;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.workitem.Attachment;
import org.kie.kogito.process.workitem.AttachmentInfo;
import org.kie.kogito.process.workitem.Comment;
import org.kie.kogito.process.workitem.Policies;
import org.kie.kogito.process.workitem.TaskModel;
import org.kie.kogito.auth.IdentityProvider;
import org.kie.kogito.auth.IdentityProviders;
import org.kie.kogito.auth.SecurityPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/persons")
@org.springframework.stereotype.Component()
public class PersonsResource {

    @org.springframework.beans.factory.annotation.Autowired()
    @org.springframework.beans.factory.annotation.Qualifier("persons")
    Process<PersonsModel> process;

    @Autowired
    ProcessService processService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public ResponseEntity<PersonsModelOutput> createResource_persons(@RequestHeader HttpHeaders httpHeaders, @RequestParam(value = "businessKey", required = false) String businessKey, @RequestBody(required = false) @javax.validation.Valid() @javax.validation.constraints.NotNull() PersonsModelInput resource, UriComponentsBuilder uriComponentsBuilder) {
        ProcessInstance<PersonsModel> pi = processService.createProcessInstance(process, businessKey, Optional.ofNullable(resource).orElse(new PersonsModelInput()).toModel(), httpHeaders, httpHeaders.getOrEmpty("X-KOGITO-StartFromNode").stream().findFirst().orElse(null));
        return ResponseEntity.created(uriComponentsBuilder.path("/persons/{id}").buildAndExpand(pi.id()).toUri()).body(pi.checkError().variables().toModel());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public List<PersonsModelOutput> getResources_persons() {
        return processService.getProcessInstanceOutput(process);
    }

    @GetMapping(value = "/schema", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public Map<String, Object> getResourceSchema_persons() {
        return JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public PersonsModelOutput getResource_persons(@PathVariable("id") String id) {
        return processService.findById(process, id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public PersonsModelOutput deleteResource_persons(@PathVariable("id") final String id) {
        return processService.delete(process, id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public PersonsModelOutput updateModel_persons(@PathVariable("id") String id, @RequestBody(required = false) @javax.validation.Valid() @javax.validation.constraints.NotNull() PersonsModelInput resource) {
        return processService.update(process, id, resource.toModel()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public PersonsModelOutput updateModelPartial_persons(@PathVariable("id") String id, @RequestBody(required = false) @javax.validation.Valid() @javax.validation.constraints.NotNull() PersonsModelInput resource) {
        return processService.updatePartial(process, id, resource.toModel()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "persons", description = "")
    public List<TaskModel> getTasks_persons(@PathVariable("id") String id, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getTasks(process, id, SecurityPolicy.of(IdentityProviders.of(user, groups))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).stream().map(org.kie.kogito.examples.demo.Persons_TaskModelFactory::from).collect(Collectors.toList());
    }

    @PostMapping(value = "/{id}/RetiredHandling/{taskId}/phases/{phase}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput completeTask_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("phase") final String phase, @RequestParam("user") final String user, @RequestParam("group") final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_3_TaskOutput model) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), model).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/RetiredHandling/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public org.kie.kogito.examples.demo.Persons_3_TaskOutput saveTask_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_3_TaskOutput model) {
        return processService.saveTask(process, id, taskId, SecurityPolicy.of(user, groups), model, org.kie.kogito.examples.demo.Persons_3_TaskOutput::fromMap).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/RetiredHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput taskTransition_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "phase", required = false, defaultValue = "complete") final String phase, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_3_TaskOutput model) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), model).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/RetiredHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public org.kie.kogito.examples.demo.Persons_3_TaskModel getTask_RetiredHandling_0(@PathVariable("id") String id, @PathVariable("taskId") String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getTask(process, id, taskId, SecurityPolicy.of(user, groups), org.kie.kogito.examples.demo.Persons_3_TaskModel::from).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/RetiredHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput abortTask_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "phase", required = false, defaultValue = "abort") final String phase, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), null).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "RetiredHandling/schema", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getSchema_RetiredHandling_0() {
        return JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id(), "RetiredHandling");
    }

    @GetMapping(value = "/{id}/RetiredHandling/{taskId}/schema", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getSchemaAndPhases_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getSchemaAndPhases(process, id, taskId, "RetiredHandling", SecurityPolicy.of(user, groups));
    }

    @PostMapping(value = "/{id}/RetiredHandling/{taskId}/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Comment> addComment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody String commentInfo, UriComponentsBuilder uriComponentsBuilder) {
        return processService.addComment(process, id, taskId, SecurityPolicy.of(user, groups), commentInfo).map(comment -> ResponseEntity.created(uriComponentsBuilder.path("/persons/{id}/RetiredHandling/{taskId}/comments/{commentId}").buildAndExpand(id, taskId, comment.getId().toString()).toUri()).body(comment)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/RetiredHandling/{taskId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public Comment updateComment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody String comment) {
        return processService.updateComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups), comment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/RetiredHandling/{taskId}/comments/{commentId}")
    public ResponseEntity deleteComment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.deleteComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups)).map(removed -> (removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/RetiredHandling/{taskId}/attachments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attachment> addAttachment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody AttachmentInfo attachmentInfo, UriComponentsBuilder uriComponentsBuilder) {
        return processService.addAttachment(process, id, taskId, SecurityPolicy.of(user, groups), attachmentInfo).map(attachment -> ResponseEntity.created(uriComponentsBuilder.path("/persons/{id}/RetiredHandling/{taskId}/attachments/{attachmentId}").buildAndExpand(id, taskId, attachment.getId()).toUri()).body(attachment)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/RetiredHandling/{taskId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Attachment updateAttachment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody AttachmentInfo attachment) {
        return processService.updateAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups), attachment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/RetiredHandling/{taskId}/attachments/{attachmentId}")
    public ResponseEntity deleteAttachment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.deleteAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups)).map(removed -> (removed ? ResponseEntity.ok() : ResponseEntity.notFound()).build()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/RetiredHandling/{taskId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Attachment getAttachment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attachment " + attachmentId + " not found"));
    }

    @GetMapping(value = "/{id}/RetiredHandling/{taskId}/attachments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Attachment> getAttachments_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user") final String user, @RequestParam(value = "group") final List<String> groups) {
        return processService.getAttachments(process, id, taskId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/RetiredHandling/{taskId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment getComment_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment " + commentId + " not found"));
    }

    @GetMapping(value = "/{id}/RetiredHandling/{taskId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Comment> getComments_RetiredHandling_0(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getComments(process, id, taskId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/AdultHandling/{taskId}/phases/{phase}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput completeTask_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("phase") final String phase, @RequestParam("user") final String user, @RequestParam("group") final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_4_TaskOutput model) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), model).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/AdultHandling/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public org.kie.kogito.examples.demo.Persons_4_TaskOutput saveTask_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_4_TaskOutput model) {
        return processService.saveTask(process, id, taskId, SecurityPolicy.of(user, groups), model, org.kie.kogito.examples.demo.Persons_4_TaskOutput::fromMap).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/AdultHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput taskTransition_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "phase", required = false, defaultValue = "complete") final String phase, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_4_TaskOutput model) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), model).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/AdultHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public org.kie.kogito.examples.demo.Persons_4_TaskModel getTask_AdultHandling_1(@PathVariable("id") String id, @PathVariable("taskId") String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getTask(process, id, taskId, SecurityPolicy.of(user, groups), org.kie.kogito.examples.demo.Persons_4_TaskModel::from).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/AdultHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput abortTask_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "phase", required = false, defaultValue = "abort") final String phase, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), null).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "AdultHandling/schema", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getSchema_AdultHandling_1() {
        return JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id(), "AdultHandling");
    }

    @GetMapping(value = "/{id}/AdultHandling/{taskId}/schema", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getSchemaAndPhases_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getSchemaAndPhases(process, id, taskId, "AdultHandling", SecurityPolicy.of(user, groups));
    }

    @PostMapping(value = "/{id}/AdultHandling/{taskId}/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Comment> addComment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody String commentInfo, UriComponentsBuilder uriComponentsBuilder) {
        return processService.addComment(process, id, taskId, SecurityPolicy.of(user, groups), commentInfo).map(comment -> ResponseEntity.created(uriComponentsBuilder.path("/persons/{id}/AdultHandling/{taskId}/comments/{commentId}").buildAndExpand(id, taskId, comment.getId().toString()).toUri()).body(comment)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/AdultHandling/{taskId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public Comment updateComment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody String comment) {
        return processService.updateComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups), comment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/AdultHandling/{taskId}/comments/{commentId}")
    public ResponseEntity deleteComment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.deleteComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups)).map(removed -> (removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/AdultHandling/{taskId}/attachments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attachment> addAttachment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody AttachmentInfo attachmentInfo, UriComponentsBuilder uriComponentsBuilder) {
        return processService.addAttachment(process, id, taskId, SecurityPolicy.of(user, groups), attachmentInfo).map(attachment -> ResponseEntity.created(uriComponentsBuilder.path("/persons/{id}/AdultHandling/{taskId}/attachments/{attachmentId}").buildAndExpand(id, taskId, attachment.getId()).toUri()).body(attachment)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/AdultHandling/{taskId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Attachment updateAttachment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody AttachmentInfo attachment) {
        return processService.updateAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups), attachment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/AdultHandling/{taskId}/attachments/{attachmentId}")
    public ResponseEntity deleteAttachment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.deleteAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups)).map(removed -> (removed ? ResponseEntity.ok() : ResponseEntity.notFound()).build()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/AdultHandling/{taskId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Attachment getAttachment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attachment " + attachmentId + " not found"));
    }

    @GetMapping(value = "/{id}/AdultHandling/{taskId}/attachments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Attachment> getAttachments_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user") final String user, @RequestParam(value = "group") final List<String> groups) {
        return processService.getAttachments(process, id, taskId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/AdultHandling/{taskId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment getComment_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment " + commentId + " not found"));
    }

    @GetMapping(value = "/{id}/AdultHandling/{taskId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Comment> getComments_AdultHandling_1(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getComments(process, id, taskId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/ChildrenHandling/{taskId}/phases/{phase}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput completeTask_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("phase") final String phase, @RequestParam("user") final String user, @RequestParam("group") final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_8_TaskOutput model) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), model).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/ChildrenHandling/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public org.kie.kogito.examples.demo.Persons_8_TaskOutput saveTask_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_8_TaskOutput model) {
        return processService.saveTask(process, id, taskId, SecurityPolicy.of(user, groups), model, org.kie.kogito.examples.demo.Persons_8_TaskOutput::fromMap).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/ChildrenHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput taskTransition_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "phase", required = false, defaultValue = "complete") final String phase, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody(required = false) final org.kie.kogito.examples.demo.Persons_8_TaskOutput model) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), model).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/ChildrenHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public org.kie.kogito.examples.demo.Persons_8_TaskModel getTask_ChildrenHandling_2(@PathVariable("id") String id, @PathVariable("taskId") String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getTask(process, id, taskId, SecurityPolicy.of(user, groups), org.kie.kogito.examples.demo.Persons_8_TaskModel::from).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/ChildrenHandling/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonsModelOutput abortTask_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "phase", required = false, defaultValue = "abort") final String phase, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.taskTransition(process, id, taskId, phase, SecurityPolicy.of(user, groups), null).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "ChildrenHandling/schema", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getSchema_ChildrenHandling_2() {
        return JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id(), "ChildrenHandling");
    }

    @GetMapping(value = "/{id}/ChildrenHandling/{taskId}/schema", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getSchemaAndPhases_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getSchemaAndPhases(process, id, taskId, "ChildrenHandling", SecurityPolicy.of(user, groups));
    }

    @PostMapping(value = "/{id}/ChildrenHandling/{taskId}/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Comment> addComment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody String commentInfo, UriComponentsBuilder uriComponentsBuilder) {
        return processService.addComment(process, id, taskId, SecurityPolicy.of(user, groups), commentInfo).map(comment -> ResponseEntity.created(uriComponentsBuilder.path("/persons/{id}/ChildrenHandling/{taskId}/comments/{commentId}").buildAndExpand(id, taskId, comment.getId().toString()).toUri()).body(comment)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/ChildrenHandling/{taskId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public Comment updateComment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody String comment) {
        return processService.updateComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups), comment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/ChildrenHandling/{taskId}/comments/{commentId}")
    public ResponseEntity deleteComment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.deleteComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups)).map(removed -> (removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/ChildrenHandling/{taskId}/attachments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attachment> addAttachment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody AttachmentInfo attachmentInfo, UriComponentsBuilder uriComponentsBuilder) {
        return processService.addAttachment(process, id, taskId, SecurityPolicy.of(user, groups), attachmentInfo).map(attachment -> ResponseEntity.created(uriComponentsBuilder.path("/persons/{id}/ChildrenHandling/{taskId}/attachments/{attachmentId}").buildAndExpand(id, taskId, attachment.getId()).toUri()).body(attachment)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}/ChildrenHandling/{taskId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Attachment updateAttachment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups, @RequestBody AttachmentInfo attachment) {
        return processService.updateAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups), attachment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}/ChildrenHandling/{taskId}/attachments/{attachmentId}")
    public ResponseEntity deleteAttachment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.deleteAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups)).map(removed -> (removed ? ResponseEntity.ok() : ResponseEntity.notFound()).build()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/ChildrenHandling/{taskId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Attachment getAttachment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("attachmentId") final String attachmentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getAttachment(process, id, taskId, attachmentId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attachment " + attachmentId + " not found"));
    }

    @GetMapping(value = "/{id}/ChildrenHandling/{taskId}/attachments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Attachment> getAttachments_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user") final String user, @RequestParam(value = "group") final List<String> groups) {
        return processService.getAttachments(process, id, taskId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/ChildrenHandling/{taskId}/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment getComment_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @PathVariable("commentId") final String commentId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getComment(process, id, taskId, commentId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment " + commentId + " not found"));
    }

    @GetMapping(value = "/{id}/ChildrenHandling/{taskId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Comment> getComments_ChildrenHandling_2(@PathVariable("id") final String id, @PathVariable("taskId") final String taskId, @RequestParam(value = "user", required = false) final String user, @RequestParam(value = "group", required = false) final List<String> groups) {
        return processService.getComments(process, id, taskId, SecurityPolicy.of(user, groups)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
